import i18n from '@/infra/i18n/index.js'

const usePreloadLanguage = (lng: string) => {
    i18n.loadLanguages(lng)
    return null
}


export default usePreloadLanguage
